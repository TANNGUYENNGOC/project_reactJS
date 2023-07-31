package com.be.controller;

import com.be.dto.product.IProductDto;
import com.be.service.IProductService;
import com.be.service.IProductTypeService;
import com.be.service.IStylesService;
import com.be.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IProductTypeService iProductTypeService;
    @Autowired
    private ISubjectService iSubjectService;
    @Autowired
    private IStylesService iStylesService;
    @GetMapping("/listProduct")
    public ResponseEntity listSearchProduct(@PageableDefault(value = 15) Pageable pageable,
                                             @RequestParam(value = "nameProduct", defaultValue = "") String nameProduct,
                                             @RequestParam(value = "nameProductType",defaultValue = "") String nameProductType,
                                             @RequestParam(value = "nameStyles",defaultValue = "") String nameStyles,
                                             @RequestParam(value = "nameSubject",defaultValue = "") String nameSubject) {
        Page<IProductDto> listProduct = iProductService.listSearchProduct(pageable, nameProduct, nameProductType, nameStyles, nameSubject);
        if (listProduct.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(listProduct, HttpStatus.OK);
    }

    @GetMapping("getProduct")
    public ResponseEntity getProduct(@RequestParam("idProduct")Integer idProduct){
        IProductDto productDto = iProductService.getProduct(idProduct);
        if (productDto == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productDto,HttpStatus.OK) ;
    }

    @GetMapping("/getSubject")
    public ResponseEntity getSubject(){
        return new ResponseEntity(iSubjectService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getProductType")
    public ResponseEntity getProductType(){
        return new ResponseEntity(iProductTypeService.findAll(),HttpStatus.OK);
    }

    @GetMapping("getStyles")
    public ResponseEntity getStyles() {
        return new ResponseEntity(iStylesService.findAll(),HttpStatus.OK);
    }
}
