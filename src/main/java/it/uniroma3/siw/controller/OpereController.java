package it.uniroma3.siw.controller;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.ArtistiService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.uploadHandle.FileUploadUtil;
import it.uniroma3.siw.validator.CollezioneValidator;
import it.uniroma3.siw.validator.OperaValidator;

@Controller
public class OpereController {
	@Autowired
	private OperaService oServ;
	
	@Autowired
	private ArtistiService artServ;
	
	@Autowired
	private OperaValidator oVal;
	
	@RequestMapping(value="/opere", method= RequestMethod.GET)
	public String tutteOpere(Model model) {
		model.addAttribute("opere", oServ.tutti());
		return "opere";
	}
	
	@RequestMapping(value="/opere/{id}", method= RequestMethod.GET)
	public String operaPerId(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", oServ.prendi(id));
		return "opera";
	}
	
	@RequestMapping(value="/admin/delete/opera/{id}", method= RequestMethod.GET)
	public String eliminaOpera(@PathVariable("id") Long id, Model model) {
		oServ.elimina(id);
		model.addAttribute("opere",oServ.tutti());
		return "opere";
	}
	
	@RequestMapping(value="/admin/addopera/{id}", method= RequestMethod.GET)
	public String addOpera (Model model, @PathVariable("id") Long id) {
		model.addAttribute("opera", new Opera());
		model.addAttribute("id",id);
		return "creaOpera";
	}
	
	@RequestMapping(value="/admin/addopera/{artid}", method= RequestMethod.POST)
	public String addOperaFinalize(@PathVariable("artid") Long artid,@ModelAttribute("opera") Opera o,Model model,BindingResult bindingResult) {
		oVal.validate(o, bindingResult);
		if(!bindingResult.hasErrors()) {
			Artista a = artServ.getArtista(artid);
			o.setCreatore(a);
			oServ.metti(o);
			a.getCreazioni().add(o);
			artServ.salva(a);
			model.addAttribute("opere", oServ.tutti());
			return "opere";
		}
		return "/admin/addopera/"+artid.toString();
	}
	
	@RequestMapping(value="/admin/addopera", method= RequestMethod.GET)
	public String addOpera (Model model) {
		model.addAttribute("opera", new Opera());
		return "creaOpera";
	}
	
	@RequestMapping(value="/admin/addopera", method= RequestMethod.POST)
	public String addOperaFinalize(@ModelAttribute("opera") Opera o,Model model,BindingResult bindingResult) {
		oVal.validate(o, bindingResult);
		if(!bindingResult.hasErrors()) {
			oServ.metti(o);
			return "opere";
		}
		return "creaOpera";
	}
	
	@RequestMapping(value="/admin/addimage/{id}",method= RequestMethod.GET)
	public String preparaFormImmagine(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id",id);
	return "imageForm";
	}
	
	@RequestMapping(value="/admin/addimage/{id}",method= RequestMethod.POST)
	public String caricaImmagine(@PathVariable("id") Long id, Model model,@RequestParam("image") MultipartFile multipartFile){
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "opereImages/" + id;
        String res = "imageForm";
        try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			res="opera";
			String finalPath = "/opereImages/" + id +'/' +fileName;
			Opera o = oServ.prendi(id);
			o.setImageUrl(finalPath);
			System.out.println(o.getImageUrl());
			oServ.metti(o);
			model.addAttribute("opera", o);
		} catch (IOException e) {
			
		}
         
        return res;
	}
}
