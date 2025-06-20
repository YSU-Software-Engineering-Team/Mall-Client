package org.ysu.mall.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.entity.Category;
import org.ysu.mall.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    // 获取所有分类
    @GetMapping("/list")
    public ApiResponse<List<Category>> listCategories() {
        List<Category> categories = categoryService.listAll();
        return ApiResponse.success(categories);
    }

    // 获取单个分类
    @GetMapping("/{id}")
    public ApiResponse<?> getCategory(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return category != null ? ApiResponse.success(category) : ApiResponse.error("分类不存在");
    }

    // 添加新分类
    @PostMapping("/add")
    public ApiResponse<?> addCategory(@RequestBody Category category) {
        boolean success = categoryService.save(category);
        return success ? ApiResponse.success("添加成功") : ApiResponse.error("添加失败");
    }

    // 更新分类
    @PostMapping("/update")
    public ApiResponse<?> updateCategory(@RequestBody Category category) {
        boolean success = categoryService.updateById(category);
        return success ? ApiResponse.success("更新成功") : ApiResponse.error("更新失败");
    }

    // 删除分类
    @PostMapping("/delete/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable Long id) {
        boolean success = categoryService.removeById(id);
        return success ? ApiResponse.success("删除成功") : ApiResponse.error("删除失败");
    }
}
