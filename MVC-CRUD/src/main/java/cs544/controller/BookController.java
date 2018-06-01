package cs544.controller;


import cs544.dao.IBookDao;
import cs544.exception.NoSuchResourceException;
import cs544.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private IBookDao bookDao;

    @RequestMapping(value="/books", method=RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "bookList";
    }

    @RequestMapping(value="/books/add", method=RequestMethod.GET)
    public String getAddView() {
        return "addBook";
    }

    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String add(@Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addBook";
        }
        bookDao.add(book);
        return "redirect:/books";
    }

    @RequestMapping(value="/books/{id}", method=RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDao.get(id));
        return "bookDetail";
    }

    @RequestMapping(value="/books/{id}", method=RequestMethod.POST)
    public String update(Book book, @PathVariable int id) {
        bookDao.update(id, book); //
        return "redirect:/books";
    }

    @RequestMapping(value="/books/delete", method=RequestMethod.POST)
    public String delete(int bookId) {
        bookDao.delete(bookId);
        return "redirect:/books";
    }


    @ExceptionHandler(value=NoSuchResourceException.class)
    public ModelAndView handle(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("e", e);
        mv.setViewName("noSuchResource");
        return mv;
    }

}
