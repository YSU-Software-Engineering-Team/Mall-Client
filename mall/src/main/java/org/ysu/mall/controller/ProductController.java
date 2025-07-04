package org.ysu.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.ProductDto;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.CategoryService;
import org.ysu.mall.service.ProductService;

/**
 * 商品管理控制器
 */
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * 添加新商品
     * @param productDto
     * @return
     */
    @PostMapping("/add")
    public ApiResponse<?> addProduct(@RequestBody ProductDto productDto) {
        try{
            if(categoryService.getCategoryById(productDto.getCategoryId())==null){
                throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
            }
            return ApiResponse.success(productService.addProduct(productDto).getProductId());
        }
        catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public ApiResponse<?> deleteProduct(@PathVariable("productId") Integer productId) {
        try{
            if(productService.deleteProduct(productId)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.PRODUCT_DELETE_ERROR);
            }
        }catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 更新商品信息
     * @param productDto
     * @return
     */
    @PostMapping("/update")
    public ApiResponse<?> updateProduct(@RequestBody @Valid ProductDto productDto) {
        try{
            if(categoryService.getCategoryById(productDto.getCategoryId())==null){
                throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
            }
            if(productService.updateProduct(productDto)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.PRODUCT_UPDATE_ERROR);
            }
        }catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * * 获取商品详情
     * @param productId
     * @return
     */
    @GetMapping("/get/{productId}")
    public ApiResponse<?> getProduct(@PathVariable("productId") Integer productId) {
        try{
            return ApiResponse.success(productService.getProductById(productId));
        }catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

    /**
     * 获取商品列表
     * @param categoryId
     * @return
     */
    @GetMapping("/list/{categoryId}")
    public ApiResponse<?> getProductList(@PathVariable("categoryId") Integer categoryId) {
        try{
            if(categoryService.getCategoryById(categoryId)==null){
                throw new BusinessException(ResultEnum.CATEGORY_NOT_FOUND);
            }
            return ApiResponse.success(productService.getProductList(categoryId));
        }catch (BusinessException e) {
            return ApiResponse.error(e.getCode());
        }
    }

}
