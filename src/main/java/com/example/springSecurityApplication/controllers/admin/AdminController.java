package com.example.springSecurityApplication.controllers.admin;

import com.example.springSecurityApplication.enumm.Status;
import com.example.springSecurityApplication.models.Image;
import com.example.springSecurityApplication.models.Order;
import com.example.springSecurityApplication.models.Person;
import com.example.springSecurityApplication.models.Product;
import com.example.springSecurityApplication.repositories.CategoryRepository;
import com.example.springSecurityApplication.repositories.OrderRepository;
import com.example.springSecurityApplication.services.OrderService;
import com.example.springSecurityApplication.services.PersonService;
import com.example.springSecurityApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final PersonService personService;
    private final OrderService orderService;

    @Autowired
    public AdminController(ProductService productService, CategoryRepository categoryRepository, PersonService personService, OrderRepository orderRepository, OrderService orderService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.orderService = orderService;

    }

    @GetMapping("")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("person", personService.getAllPerson());
        return "admin/admin";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // ?????????? ???? ???????????????????? ???????????????? ?? ???? ?????????? ????????????->??????????????????????
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {
        if(bindingResult.hasErrors())
        {
            return "product/addProduct";
        }

        if(file_one != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_two != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_three != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_four != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_five != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }



    // ?????????? ???? ?????????????????????? ???????????????? ?? ???????????????????????? ???????????????????????????? ??????????????
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }


    // ?????????? ???? ???????????????????????????? ????????????
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/product/info/{id}";
    }
/*-----------------------------------*/
    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.getPersonId(id));

        return "admin/editUser";
    }

    @PostMapping("/editUser/{id}")
    public String editUser(@ModelAttribute("person") Person person, @PathVariable("id") int id){

        personService.updatePerson(id, person);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        personService.deleteUser(id);
        return "redirect:/admin";
    }

/*----------------ORDERS-----------------*/

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/viewOrder/{id}")
    public String viewOrder(Model model, @PathVariable("id") int id){
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("ListOfStatus", orderService.getAllStatus());
        return "admin/viewOrder";
    }

    @PostMapping("/editStatus/{id}")
    public String updateOrderStatus(@RequestParam(value = "status")String set_status,  @PathVariable("id") int id ){
        Order order = orderService.getById(id);
        order.setStatus(Status.valueOf(set_status));
        orderService.updateOrder(order);
        return "redirect:/admin/viewOrder/{id}";
    }

    @GetMapping("/order")
    public String ordersUser(Model model){
        List<Order> listAllOrders = orderService.getAllOrders();
        model.addAttribute("order", listAllOrders);
        return "/admin/order";
    }

    @PostMapping("/searchByNumber")
    public String productSearchByName(@RequestParam("search")  String lastSymbols, Model model){
        model.addAttribute("search_order", orderService.findByLastNumberSymbols(lastSymbols));
        model.addAttribute("value_search", lastSymbols);
        model.addAttribute("orders", orderService.getAllOrders());
        return "/admin/order";
    }
}



