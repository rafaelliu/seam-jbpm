package net.rafaelliu.seam.jbpm.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.rafaelliu.seam.jbpm.domain.Documento;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;


@Name("fileUploadBean")
public class FileUploadBean{
    
    @In
	private Documento documento;

    public void listener(UploadEvent event) {
		try {
	        UploadItem item = event.getUploadItem();
	        byte[] fileBytes = getBytesFromFile(item);
	        documento.setNome(item.getFileName());
	        documento.setMime(item.getContentType());
	        documento.setTamanho(fileBytes.length);
	        documento.setBinario(fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    
    private byte[] getBytesFromFile(UploadItem uploadItem) throws IOException {
    	File file = uploadItem.getFile();
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        is.close();
        return bytes;
    }
      
}