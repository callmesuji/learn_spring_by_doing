package com.careerit.scart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.careerit.scart.domain.Product;
import com.careerit.scart.service.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = { "/addproductview"})
	public String addProductV() {
		return "addproductview";
	}

	@RequestMapping(value = { "/addnewproduct" }, method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, Model model) {
		productService.addProduct(product);
		return "redirect:/admin/home";
	}


	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String viewAdmin(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);
		return "home";
	}

//					 deleting product
	@RequestMapping(value = "/deleteproduct/{pid}")
	public ModelAndView DeleteProduct(@PathVariable("pid") long pid) {
		productService.deleteProduct(pid);
		return new ModelAndView("redirect:/admin/home");
	}
//                    updating product

	@RequestMapping(value = { "/updateproductview/{pid}" })
	public String UpdateProductView(@PathVariable("pid") long pid, Model model) {
		Product product = productService.getProductById(pid);
		model.addAttribute(product);
		return "updateproductview";
	}

	@RequestMapping(value = { "/updateproduct" }, method = RequestMethod.POST)
	public ModelAndView UpdateProduct(@ModelAttribute("product") Product product, Model model) {
		Product productList = productService.updateProduct(product);
		model.addAttribute("productList", productList);
		return new ModelAndView("redirect:/admin/home");
	}
	



}
