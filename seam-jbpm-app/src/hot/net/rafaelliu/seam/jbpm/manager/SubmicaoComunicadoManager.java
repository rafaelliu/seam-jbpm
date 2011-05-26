package net.rafaelliu.seam.jbpm.manager;

import net.rafaelliu.seam.jbpm.domain.Documento;
import net.rafaelliu.seam.jbpm.process.AcmeProcess;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;

@Name("submicaoCompraManager")
public class SubmicaoComunicadoManager {

	@In
	private StatusMessages statusMessages;

	@In
	private AcmeProcess acmeProcess;
	
	@In
	private Documento documento;
	
	public String submeter() {
		acmeProcess.adicionarDocumento(documento);
		statusMessages.add("Tarefa #{taskInstance.id} submetida");
		return "ok";

	}

}
