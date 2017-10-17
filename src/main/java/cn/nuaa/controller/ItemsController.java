package cn.nuaa.controller;

import cn.nuaa.controller.validation.ValidGroup1;
import cn.nuaa.exception.CustomException;
import cn.nuaa.po.ItemsCustom;
import cn.nuaa.po.ItemsQueryVo;
import cn.nuaa.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017-08-07.
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;


    /* 商品分类
 * @ModelAttribute 方法通常被用来填充一些公共需要的属性或数据，比如一个下拉列表所预设的几种状态
 * itemtypes表示最终将方法返回值放在request中的key
 */
    @ModelAttribute("itemtypes")
    public Map<String, String> getItemTypes() {
        Map<String, String> itemTypes = new HashMap<String, String>();
        itemTypes.put("101", "数码");
        itemTypes.put("102", "母婴");

        return itemTypes;
    }

    // 商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
        // 测试forward后request是否可以共享
        System.out.println(request.getParameter("id"));
        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }



    /*
        @ReqestParam
        通过使用@RequestParam对简单类型的参数进行绑定，如果不使用@RequestParam，要求request传入的参数名称和controller
        方法的形参名称一致
        使用该注解不用限制request传入参数的名称
        <a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">
     */
    @RequestMapping(value = "/editItems", method = {RequestMethod.GET, RequestMethod.POST})
    public String editItems(HttpServletRequest request, Model model, @RequestParam(value = "id", required = true) Integer items_id) throws Exception {

        //测试forward后request是否可以共享
        System.out.println("request-> id ： " + request.getParameter("id"));

        //调用service根据商品的id查询商品
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
        if (itemsCustom == null) {
            throw new CustomException("修改的商品信息不存在!");
        }
        //相当于mv.addObject("items",itemsCustom),将model数据填充到request域
        model.addAttribute("items", itemsCustom);

        return "items/editItems";
    }

    //商品信息修改后提交,@ModelAttribute("items")用于pojo类型数据的回显，不能用于简单类型的数据回显
    @RequestMapping(value = "/editItemsSubmit",method = {RequestMethod.GET,RequestMethod.POST})
    public String editItemsSubmit(Model model,HttpServletRequest request, Integer id,
         @ModelAttribute("items") @Validated(value={ValidGroup1.class}) ItemsCustom itemsCustom,
                                  BindingResult bindingResult,
                                  MultipartFile items_pic) throws Exception{

        // 获取校验错误信息
        if (bindingResult.hasErrors()) {
            // 输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();

            for (ObjectError objectError : allErrors) {
                // 输出错误信息
                System.out.println(objectError.getDefaultMessage());
            }
            // 将错误信息传到页面
            model.addAttribute("allErrors", allErrors);

            //不使用@ModelAttribute("items")的话，也可以直接使用model将提交pojo回显到页面
            model.addAttribute("items", itemsCustom);

            // 出错重新到商品修改页面
            return "items/editItems";

    }
        //原始名称
        String originalFilename = items_pic.getOriginalFilename();
        //上传图片
        if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){

            //存储图片的物理路径
            String pic_path = "E:\\Tomcat_temp\\pic\\";

            //新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片
            File newFile = new File(pic_path + newFileName);

            //将内存中的数据写入磁盘
            items_pic.transferTo(newFile);

            //将新图片名称写到itemsCustom中
            itemsCustom.setPic(newFileName);

        }

        //调用service更新商品的信息
        itemsService.updateItems(id, itemsCustom);

        return "forward:queryItems.action";
    }

    //查询商品信息，输出json
    ///itemsView/{id}里边的{id}表示占位符，通过@PathVariable获取占位符中的参数，
    //如果占位符中的名称和形参名一致，在@PathVariable可以不指定名称
    @RequestMapping("/itemsView/{id}")
    @ResponseBody
    public ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{

        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        return itemsCustom;

    }

    // 批量删除 商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {

        // 调用service批量删除商品
        // ...

        return "success";

    }

    // 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        modelAndView.setViewName("items/editItemsQuery");

        return modelAndView;

    }

    // 批量修改商品提交
    // 通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中itemsList属性中。
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)
            throws Exception {

        return "success";
    }

}
