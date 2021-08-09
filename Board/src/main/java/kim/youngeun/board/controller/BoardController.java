package kim.youngeun.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kim.youngeun.board.model.Board;
import kim.youngeun.board.model.BoardForm;
import kim.youngeun.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@GetMapping("/")
	public String list(Model model) {
		
		List<Board> boardList = boardService.searchAll();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("board", new Board());
		
		return "list";
	}
	
	@PostMapping("/")
	public String createPost(@Valid Board board, BindingResult bindingResult, Model model) {
	
	if(bindingResult.hasErrors()) {
		List<Board> boardList = boardService.searchAll();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("board", board);
		
		return "list";
	}
	
		boardService.writePost(board);
		
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable(name="id") int boardId, Model model) {
		
		Board board = boardService.findByno(boardId);
		
		model.addAttribute("board", board);
		
		return "updateform";
	}
	
	
	@PostMapping("/update/form/{id}")
	public String updateForm(@PathVariable("id") int id, BoardForm form) {
		
		Board board = boardService.getBoardId(id);
		
		board.setWriter(form.getWriter());
		board.setTitle(form.getTitle());
		board.setContent(form.getContent());
		
		boardService.updateBoard(board);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteBoard(@PathVariable("id") int id) {
		
		boardService.deleteBoard(id);
		
		return "redirect:/";
	}
	
	
	
}
