package com.example.springSecurityApplication.controllers.product;

import com.example.springSecurityApplication.repositories.ProductRepository;
import com.example.springSecurityApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";
    }

    @GetMapping("/info/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }

    @PostMapping("/search")
    public String productSearch(@RequestParam("search") String search,
                                @RequestParam(value = "ot", required = false, defaultValue = "") String ot,
                                @RequestParam(value = "do", required = false, defaultValue = "") String Do,
                                @RequestParam(value = "price", required = false, defaultValue = "")String price,
                                @RequestParam(value = "contact", required = false, defaultValue = "") String contact, Model model)
    {
        //ПО ВОЗВРАСТАНИЮ
        if(price.equals("sorted_by_ascending_price")){

            //С ДИАПАЗОНОМ!!!
            if(!ot.isEmpty() & !Do.isEmpty()){
                if(!contact.isEmpty())
                {
                    if(contact.equals("junior")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                    } else if(contact.equals("middle")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                    }else if(contact.equals("senior")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                    }
                }
                else {
                    model.addAttribute("search_product", productRepository.findByTitlePriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
                }
                //БЕЗ ДИАПАЗОНА
            }else {
                if (!contact.isEmpty()) {
                    if (contact.equals("junior")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryAsc(search.toLowerCase(), 1));
                    } else if (contact.equals("middle")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryAsc(search.toLowerCase(), 2));
                    } else if (contact.equals("senior")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryAsc(search.toLowerCase(), 3));
                    }
                } else {
                    model.addAttribute("search_product", productRepository.findByTitleAsc(search.toLowerCase()));
                }
            }

        //ПО УБЫВАНИЮ!!!
        } else if (price.equals("sorted_by_descending_price")){
            //С ДИАПАЗОНОМ!!!
            if(!ot.isEmpty() & !Do.isEmpty()){
                if(!contact.isEmpty())
                {
                    if(contact.equals("junior")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                    } else if(contact.equals("middle")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                    }else if(contact.equals("senior")){
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                    }
                }
                else {
                    model.addAttribute("search_product", productRepository.findByTitlePriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
                }
                //БЕЗ ДИАПАЗОНА
            }else {
                if (!contact.isEmpty()) {
                    if (contact.equals("junior")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryDesc(search.toLowerCase(), 1));
                    } else if (contact.equals("middle")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryDesc(search.toLowerCase(), 2));
                    } else if (contact.equals("senior")) {
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryDesc(search.toLowerCase(), 3));
                    }
                } else {
                    model.addAttribute("search_product", productRepository.findByTitleDesc(search.toLowerCase()));
                }
            }
        }
        model.addAttribute("value_search", search);
        model.addAttribute("value_price_ot", ot);
        model.addAttribute("value_price_do", Do);
        model.addAttribute("products", productService.getAllProduct());
        return "/product/search";
    }




    @PostMapping("/searchByName")
    public String productSearchByName(@RequestParam("search") String search, Model model){
        model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));

        model.addAttribute("value_search", search);
        model.addAttribute("products", productService.getAllProduct());
        return "/admin/admin";

    }
}


