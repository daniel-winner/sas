package com.js.sas.controller;

import com.alibaba.fastjson.JSON;
import com.js.sas.dto.PartnerDTO;
import com.js.sas.entity.PartnerEntity;
import com.js.sas.repository.PartnerRepository;
import com.js.sas.utils.ResultCode;
import com.js.sas.utils.ResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName PartnerController
 * @Description 往来单位Controller
 * @Author zc
 * @Date 2019/6/13 11:26
 **/
@RestController
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerRepository partnerRepository;

    @ApiOperation(value = "根据名称模糊查询往来单位，必须设置数量", notes = "数据来源：用友；数据截止日期：昨天")
    @PostMapping("/getPartnerByNameLikeLimit")
    public Mono<Object> getPartnerByNameLikeLimit(PartnerDTO partnerDTO) {
        // 往来单位名称
        partnerDTO.setName(Optional.ofNullable(partnerDTO.getName()).orElse(""));

        List<PartnerEntity> partnerList = partnerRepository.findByNameLikeValidLimit(partnerDTO.getName(), partnerDTO.getLimit());

        return Mono.just(ResultUtils.getResult(ResultCode.成功, JSON.toJSONString(partnerList)));
    }

}
