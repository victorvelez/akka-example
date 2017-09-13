package com.akka.example;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld  extends UntypedActor {

	  @Override
	  public void preStart() {
	    // create the greeter actor
		  System.err.println("preStart-HelloWorld");
	    final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
	    // tell it to perform the greeting
	    greeter.tell(Greeter.Msg.GREET, getSelf());
	  }

	  @Override
	  public void onReceive(Object msg) {
		  System.err.println("onReceive-HelloWorld");
	    if (msg == Greeter.Msg.DONE) {
	      // when the greeter is done, stop this actor and with it the application
	      getContext().stop(getSelf());
	    } else
	      unhandled(msg);
	  }
	}