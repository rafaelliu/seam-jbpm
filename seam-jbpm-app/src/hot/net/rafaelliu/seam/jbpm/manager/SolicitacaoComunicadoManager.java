package net.rafaelliu.seam.jbpm.manager;

import net.rafaelliu.seam.jbpm.process.AcmeProcess;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;

@Name("solicitacaoComunicadoManager")
public class SolicitacaoComunicadoManager {

	@In
	private StatusMessages statusMessages;
	
	@In
	private AcmeProcess acmeProcess;
	
	public void solicitar() {
		acmeProcess.iniciarProcesso();
		statusMessages.add("Solicitação de comunicado iniciada");
	}

}
