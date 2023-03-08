package movielistmain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MovielistmainController {
	
	@GetMapping(value="/")
	@ResponseBody
	public String index() {
		return "안녕하세요 리액트!!";
	}

}
