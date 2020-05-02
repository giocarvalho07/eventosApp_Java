package com.eventosAPP.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosAPP.model.Convidado;
import com.eventosAPP.model.Evento;
import com.eventosAPP.repository.ConvidadoRepository;
import com.eventosAPP.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@RequestMapping( value="/evento", method=RequestMethod.GET )
	public String form() {
		return "evento/formEvento";
		// trazer lista de dados de eventos
	}
	
	
	@RequestMapping( value="/evento", method=RequestMethod.POST )
	public String form(@Valid Evento evento,
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos estão preenchidos!");
			return "redirect:/evento";
		}
		else {
			eventoRepository.save(evento);
			attributes.addFlashAttribute("mensagem","Evento cadastrado com sucesso!");
			return "redirect:/evento";
			// criar cadastro de eventos
		}
	}
	
	@RequestMapping("/home")
	public ModelAndView lista() {
		ModelAndView modelView = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		modelView.addObject("eventos", eventos);
		return modelView;
		// listar os eventos 
	}
	
	@RequestMapping("/home/delete")
	public String deletarEvento(long id) {
		Evento evento = eventoRepository.findById(id);
		eventoRepository.delete(evento);
		return "redirect:/home";
		
	}
	
	@RequestMapping(value="/{idEvento}", method = RequestMethod.GET)
	public ModelAndView detalheEvento(@PathVariable ("idEvento") long idEvento) {
		Evento evento = eventoRepository.findById(idEvento);
		ModelAndView modelView = new ModelAndView("evento/detalheEvento");
		modelView.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		modelView.addObject("convidados", convidados);
		return modelView;
		// mostrar os detalhes do evento por id em uma outra view 
	}
	
	@RequestMapping(value="/{idEvento}", method = RequestMethod.POST)
	public String mostrarEvento (@PathVariable ("idEvento") long idEvento, @Valid Convidado convidado, 
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos estão preenchidos!");
 		return "redirect:/{idEvento}";
		}
		else {
			Evento evento = eventoRepository.findById(idEvento);
			convidado.setEvento(evento);
			convidadoRepository.save(convidado);
			attributes.addFlashAttribute("mensagem","Convidado adicionado com sucesso!");

			return "redirect:/{idEvento}";
		}
	}
	
	@RequestMapping("convidado/delete")
	public String deletarConvidado(String rg) {
		Convidado convidado = convidadoRepository.findByRg(rg);
		convidadoRepository.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long idLong = evento.getId();
		String idCodigo = " " + idLong;
		return "redirect:/" + idCodigo;
		
	}
}
