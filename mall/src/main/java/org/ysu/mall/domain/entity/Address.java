package org.ysu.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName address
 */
@TableName(value ="address")
@Data
@Accessors(chain = true)
public class Address {
    /**
     * 地址ID
     */
    @TableId(type = IdType.AUTO)
    private Integer addressId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 省市区
     */
    private String region;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 默认地址
     */
    private Integer isDefault;

    /**
     * 
     */
    private Date createdAt;
}