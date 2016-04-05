package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;

/**
 * Created by Greg on 05.04.2016.
 */
public class mojatoTest {
    private static final String VALID_SERVER = "inf.ug.edu.pl";
    private static final String INVALID_SERVER = "inf.ug.edu.eu";

    private static final String VALID_MESSAGE = "wiadomosc";
    private static final String INVALID_MESSAGE = "aa";

    private Messenger messenger;
    private MessageService msMock;

    @Before
    public void setUP() throws MalformedRecipientException {
        msMock = mock(MessageService.class);
        messenger = new Messenger(msMock);
    }

    @Test
    public void sendingValidRecipientAndServer()
            throws MalformedRecipientException {


        when(msMock.send(VALID_SERVER, VALID_MESSAGE)).thenReturn(
                SendingStatus.SENT);

        when(msMock.checkConnection(VALID_SERVER)).thenReturn(
                ConnectionStatus.SUCCESS);


        assertThat(messenger.testConnection(VALID_SERVER), equalTo(0));
        assertThat(messenger.sendMessage(VALID_SERVER, VALID_MESSAGE),
                either(equalTo(0)).or(equalTo(1)));

        verify(msMock, atLeastOnce()).send(VALID_SERVER, VALID_MESSAGE);
    }

    @Test
    public void sendingInvalidServer() throws MalformedRecipientException {

        when(msMock.checkConnection(INVALID_SERVER)).thenReturn(
                ConnectionStatus.FAILURE);
        when(msMock.send(INVALID_SERVER, VALID_MESSAGE)).thenReturn(
                SendingStatus.SENDING_ERROR);


        assertThat(messenger.testConnection(INVALID_SERVER), equalTo(1));
        assertEquals(1, messenger.sendMessage(INVALID_SERVER, VALID_MESSAGE));

        verify(msMock).checkConnection(INVALID_SERVER);
    }



    @Test
    public void sendingInvalidReceipient() throws MalformedRecipientException {

        when(msMock.send(VALID_SERVER, INVALID_MESSAGE)).thenThrow(
                new MalformedRecipientException());

        assertEquals(2, messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE));

        verify(msMock).send(VALID_SERVER, INVALID_MESSAGE);
    }

    @Test
    public void sendingConnectionStatus() {

        ArgumentCaptor<String> capturedServer = ArgumentCaptor.forClass(String.class);

        assertEquals(1, messenger.testConnection(INVALID_SERVER));
        assertEquals(INVALID_SERVER, capturedServer.getValue());

        verify(msMock).checkConnection(capturedServer.capture());
    }


}
