package org.thingml.generated.network;

import org.thingml.generated.messages.*;
import no.sintef.jasm.*;
import no.sintef.jasm.ext.*;

import java.io.PrintStream;
import java.util.Scanner;

public class StdIOJava extends Component {
	private final stdioStringProtocol formatter = new stdioStringProtocol();

	final Scanner stdin = new Scanner(System.in);
	final PrintStream stdout = System.out;

	/*$PORTS$*/
private Port TTYin_temp_port;
public Port getTTYin_temp_port(){return TTYin_temp_port;}

private Port TTYin_lum_mot_port;
public Port getTTYin_lum_mot_port(){return TTYin_lum_mot_port;}

private Port get_values_port;
public Port getGet_values_port(){return get_values_port;}


	private void parse(final String payload) {
        final Event event = formatter.instantiate(payload);
if (event != null) get_values_port.send(event);
if (event != null) TTYin_lum_mot_port.send(event);
if (event != null) TTYin_temp_port.send(event);

	}

	@Override
	public void run() {
		new Thread(){
			public void run() {
				while (stdin.hasNext()) {
					parse(stdin.next());
				}
			}
		}.start();
		while (active.get()) {
			try {
				final Event e = queue.take();//should block if queue is empty, waiting for a message
				final String payload = formatter.format(e);
				if (payload != null)
					stdout.println(payload);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
	}

	@Override
	public Component buildBehavior(String id, Component root) {
        /*$INIT PORTS$*/
TTYin_temp_port = new Port("TTYin_temp", this);

TTYin_lum_mot_port = new Port("TTYin_lum_mot", this);

get_values_port = new Port("get_values", this);

		final AtomicState init = new AtomicState("Init");
		behavior = new CompositeState("default");
		behavior.add(init);
		behavior.initial(init);
		return this;
	}
}

