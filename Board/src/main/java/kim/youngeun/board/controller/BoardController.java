package kim.youngeun.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/write")
	public String writingMove(Model model) {
		
		model.addAttribute("board", new Board());
		
		return "write";
	}
	
	
	@PostMapping("/write")
	public String createPost(@Valid @ModelAttribute Board board, BindingResult bindingResult, Model model) {
	
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
	public String update(@PathVariable(name="id") Long boardId, Model model) {
		
		Board board = boardService.findByno(boardId);
		
		model.addAttribute("board", board);
		
		return "updateform";
	}
	
	
	@PostMapping("/update/form/{id}")
	public String updateForm(@PathVariable("id") Long id, BoardForm form) {
		
		Board board = boardService.getBoardId(id);
		
		board.setWriter(form.getWriter());
		board.setTitle(form.getTitle());
		board.setContent(form.getContent());
		
		boardService.updateBoard(board);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteBoard(@PathVariable("id") Long id) {
		
		boardService.deleteBoard(id);
		
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
		
		List<BoardForm> boardList = boardService.searchPost(keyword);
				
		model.addAttribute("boardList", boardList);
		
		return "list";
	}
	
}
