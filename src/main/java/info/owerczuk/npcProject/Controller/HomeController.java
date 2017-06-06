package info.owerczuk.npcProject.Controller;

import info.owerczuk.npcProject.DAO.ContactsRepository;
import info.owerczuk.npcProject.DAO.UserRepository;
import info.owerczuk.npcProject.Domain.Contacts;
import info.owerczuk.npcProject.Service.LoginService;
import info.owerczuk.npcProject.ViewModel.LoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List;

/**
 * Created by Boberkowy on 30.05.2017.
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/indeksik")
    public String index(Model model) {

        LoginViewModel loginViewModel = new LoginViewModel();
        model.addAttribute("login", loginViewModel);
        return "index";
    }

    @RequestMapping(value = "/indeksik", method = RequestMethod.POST)
    public String loginPage(@Valid LoginViewModel loginViewModel, BindingResult bindingResult, Model model) {
        try {
            if (loginService.checkLogin(loginViewModel.getUsername(), loginViewModel.getPassword(), userRepository)) {
                httpSession.setAttribute("login", loginViewModel.getUsername());
                model.addAttribute("sessionVariable",httpSession.getAttribute("login").toString());
                return "redirect:/getallcontacts";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("login", loginViewModel);
        return "redirect:/getallcontacts";
    }

    @RequestMapping("/getallcontacts")
    public String allContacts(Model model,ModelMap modelMap) {
        try {
            List<Contacts> contacts = contactsRepository.getAllByIdIsNotNull();
            if(httpSession.getAttribute("login") == null){
                model.addAttribute("sessionVariable","null");
                modelMap.addAttribute("contacts", contacts);
                return "/getallcontacts";
            }else{
                String session = httpSession.getAttribute("login").toString();
                model.addAttribute("sessionVariable", session);
                modelMap.addAttribute("contacts", contacts);
                return "/getallcontacts";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/indeksik";
    }

    @RequestMapping(value = "/getContact/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Contacts> get(@PathVariable("id") Long id) throws Exception{
        Contacts contacts = contactsRepository.findOne(id);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public void addContact(@RequestBody Contacts contacts,HttpServletResponse response) throws Exception{
        if(httpSession.getAttribute("login") =="null"){
            response.sendRedirect("/getallcontacts");
        }
        System.out.println("SUBKATEGORIA : " + contacts.getSubcategory());
        contactsRepository.save(contacts);
        response.sendRedirect("/getallcontacts");
    }

    @RequestMapping(value = "/deleteContact/{id}",
            method = RequestMethod.GET)
    public void  deleteContact(@PathVariable("id") Long id, HttpServletResponse response) throws Exception{
        if(httpSession.getAttribute("login") =="null"){
            response.sendRedirect("/getallcontacts");
        }
        contactsRepository.delete(id);
        response.sendRedirect("/getallcontacts");
    }

    @RequestMapping(value = "/editContact/{id}",
                    method = RequestMethod.GET)
    public String editContact(@PathVariable("id") long id, Model model){
        if(httpSession.getAttribute("login") =="null"  || httpSession.getAttribute("login") == null){
            return ("redirect:/getallcontacts");
        }
      Contacts contacts = contactsRepository.findOne(id);
      model.addAttribute("contact", contacts);

      return "editContact";
    }

    @RequestMapping(value = "/editContact/{id}",
            method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void editContact(@PathVariable Long id, Contacts contacts, BindingResult bindingResult, HttpServletResponse response) throws Exception{
        if(httpSession.getAttribute("login").toString().equals("null") || httpSession.getAttribute("login") == null){
            response.sendRedirect("/getallcontacts");
        }
        System.out.println("SUBKATEGORIA: " + contacts.getSubcategory());
        contactsRepository.save(contacts);
        response.sendRedirect("/getallcontacts");
    }

}
