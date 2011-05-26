package net.rafaelliu.seam.jbpm;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("authenticator")
public class Authenticator {
	@Logger
	private Log log;

	@In
	Identity identity;
	
	@In
	Credentials credentials;
	
	@In
	Actor actor;

	public boolean authenticate() {
		log.info("authenticating {0}", credentials.getUsername());
		if ("cordenador".equals(credentials.getUsername())) {
			identity.addRole("cordenadores");
			actor.setId("cordenador");
			actor.getGroupActorIds().add("cordenadores");
			return true;
		}
		if ("autor".equals(credentials.getUsername())) {
			identity.addRole("autores");
			actor.setId("autor");
			actor.getGroupActorIds().add("autores");
			return true;
		}
		if ("revisor".equals(credentials.getUsername())) {
			identity.addRole("revisores");
			actor.setId("revisor");
			actor.getGroupActorIds().add("revisores");
			return true;
		}
		if ("aprovador".equals(credentials.getUsername())) {
			identity.addRole("aprovadores");
			actor.setId("aprovador");
			actor.getGroupActorIds().add("aprovadores");
			return true;
		}
		return false;
	}

}
