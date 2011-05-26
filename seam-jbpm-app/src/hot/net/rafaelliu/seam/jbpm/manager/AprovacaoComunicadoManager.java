package net.rafaelliu.seam.jbpm.manager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;

@Name("aprovacaoComunicadoManager")
public class AprovacaoComunicadoManager {

	@In
	private StatusMessages statusMessages;
	
	public String aprovar() {
		statusMessages.add("Tarefa #{taskInstance.id} aprovada");
		return "aprovar";

	}

	public String reprovar() {
		statusMessages.add("Tarefa #{taskInstance.id} reprovada");
		return "reprovar";
	}

}
