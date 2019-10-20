package fi.serverprogrammingcourse.runningdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fi.serverprogrammingcourse.runningdatabase.domain.FileDummy;
import fi.serverprogrammingcourse.runningdatabase.domain.FileRepository;

import java.io.IOException;
import java.util.Optional;

@Controller
public class FileController {
	
	@Autowired
	private FileRepository frepository; 
	
	@Value("${upload.path}")
	private String uploadFolder;
	
	@GetMapping("/uploadfile")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
    	// Image Base64.getEncoder().encodeToString(file.file)
    	// <img  th:src="@{'data:image/jpeg;base64,'+${file.file}}" />
        if (file.isEmpty()) {
        	model.addAttribute("msg", "Upload failed");
            return "uploadstatus";
        }

        try {
        	byte[] bytes = file.getBytes();
            FileDummy fileDummy = new FileDummy(file.getOriginalFilename(), file.getContentType(), bytes);
            frepository.save(fileDummy);
            
            model.addAttribute("msg", "File " + file.getOriginalFilename() + "uploaded");
    
            return "redirect:/files";
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadstatus";
    }
	    
	    @GetMapping("/files")
	    public String fileList(Model model) {
	    	model.addAttribute("files", frepository.findAll());  	
	    	return "filelist";
	    }
	    
		@GetMapping("/file/{id}")
		public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
			Optional<FileDummy> fileOptional = frepository.findById(id);
			
			if(fileOptional.isPresent()) {
				FileDummy file = fileOptional.get();
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
						.body(file.getFile());	
			}
			
			return ResponseEntity.status(404).body(null);
		}    
}
