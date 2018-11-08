package ro.tuc.dsrl.ds.handson.assig.three.producer.start;

import java.io.IOException;

import ro.tuc.dsrl.ds.handson.assig.three.producer.connection.QueueServerConnection;
import ro.tuc.dsrl.ds.handson.assig.three.producer.entities.DVD;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 *          Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-one-client
 * @Since: Sep 1, 2015
 * @Description:
 *	Starting point for the Producer Client application. This
 *	application will send several messages to be inserted
 *  in the queue server (i.e. to be sent via email by a consumer).
 */
public class ClientStart {
	private static final String HOST = "localhost";
	private static final int PORT = 8888;

	private ClientStart() {
	}

	public static void main(String[] args) throws IOException {
		QueueServerConnection queue = new QueueServerConnection(HOST, PORT);

				DVD dv1 = new DVD("Mortal Kombat XXL", 2010, 50);
				queue.writeMessage(dv1.toString());
		} 
	}

