package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
@Accessors(chain = true)
public class Product {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 销售价
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 主图URL
     */
    private List<String> mainImages;

    /**
     * 子图URL集合(JSON格式)
     */
    private List<String> subImages;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 状态
     */
    private Object status;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;
}