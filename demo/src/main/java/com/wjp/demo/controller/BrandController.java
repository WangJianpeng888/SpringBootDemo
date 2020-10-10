package com.wjp.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wjp.demo.common.FileResult;
import com.wjp.demo.common.ResJsonMap;
import com.wjp.demo.common.SearchContent;
import com.wjp.demo.common.UUIDUtil;
import com.wjp.demo.entity.Brand;
import com.wjp.demo.service.BrandService;

@RestController
@RequestMapping("brand")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	/*
	 *   新增一个品牌
	 */
	@RequestMapping(value = "/brandadd.do",method = RequestMethod.POST)
	public String brandAdd(@RequestBody Brand brand) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			brand.setbFinalTime(sdf.format(new Date()));
			brandService.addBrand(brand);
			return new ResJsonMap("000000","添加成功！").resJsonMap();
		}catch (Exception e) {
			return new ResJsonMap("000001","添加失败！").resJsonMap();
		}
		
	}
	/*
	 *   更新一个品牌
	 */
	@RequestMapping(value = "/brandupdate.do",method = RequestMethod.POST)
	public String brandupdate(@RequestBody Brand brand) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			brand.setbFinalTime(sdf.format(new Date()));
			brandService.updateBrand(brand);
			return new ResJsonMap("000000","更新成功！").resJsonMap();
		}catch (Exception e) {
			return new ResJsonMap("000001","更新失败！").resJsonMap();
		}
		
	}
	/*
	 *   校验数据库中code是否已存在
	 */
	@RequestMapping(value = "/checkCode.do",method = RequestMethod.POST)
	public String checkCode(@RequestBody Brand brand) {
		String code=brand.getbCode();
		List<Brand> list=brandService.findByBCode(code);
		if(list.size()==0) {
			return new ResJsonMap("000000","尚未存在！").resJsonMap();
		}
		return new ResJsonMap("000001","此编码已存在！").resJsonMap();
		
	}
	
	/*
	 *   设置为启用
	 */
	@RequestMapping(value = "/setStateUse.do",method = RequestMethod.POST)
	public String setStateUse(@RequestBody Brand brand) {
		int id=brand.getbId();
		System.out.println(id);
		try {
			brandService.setStateUseByBId(id);
			return new ResJsonMap("000000","设置成功！").resJsonMap();
		} catch (Exception e) {
			System.out.println(e);
			return new ResJsonMap("000001","设置失败，请联系管理员！").resJsonMap();
		}
	}
	
	/*
	 *   设置为禁用
	 */
	@RequestMapping(value = "/setStateNotUse.do",method = RequestMethod.POST)
	public String setStateNotUse(@RequestBody Brand brand) {
		int id=brand.getbId();
		System.out.println(id);
		try {
			brandService.setStateNotUseByBId(id);
			return new ResJsonMap("000000","设置成功！").resJsonMap();
		} catch (Exception e) {
			System.out.println(e);
			return new ResJsonMap("000001","设置失败，请联系管理员！").resJsonMap();
		}
	}
	
	/*
	 *   根据查询条件查询数据
	 */
	@RequestMapping(value = "/doQueryData.do",method = RequestMethod.POST)
	public List<Brand> doQueryData(@RequestBody SearchContent con) {
			return brandService.findBySearchCon(con);
	}
	
	/*
	 *   根据查询条件查询数据总量
	 */
	@RequestMapping(value = "/doQueryDataCount.do",method = RequestMethod.POST)
	public String doQueryDataCount(@RequestBody SearchContent con) {
		 int count=brandService.countBySearchCon(con);
		 return new ResJsonMap("000000",count+"").resJsonMap();
	}
	
	/*
	 *   查询全部数据
	 */
	@RequestMapping(value = "/findAll.do",method = RequestMethod.POST)
	public List<Brand> findAll() {
			return brandService.findAll();
	}
	/*
	 *   查询全部启用数据
	 */
	@RequestMapping(value = "/findAllQy.do",method = RequestMethod.POST)
	public List<Brand> findAllQy() {
			return brandService.findByBState(1);
	}
	
	/*
	 *    批量启用
	 */
	@RequestMapping(value = "/doPlQy.do",method = RequestMethod.POST)
	public String doPlQy(@RequestBody Map map) {
		JSONObject json = (JSONObject) JSONObject.parse(JSON.toJSONString(map));
		JSONArray arr=json.getJSONArray("brandlist");
		try {
			for (Object object : arr) {
				JSONObject arrJsons = (JSONObject) JSONObject.parse(object.toString());
				int id=arrJsons.getInteger("bId");
				brandService.setStateUseByBId(id);
			}
			return new ResJsonMap("000000","成功").resJsonMap();
		} catch (Exception e) {
			 System.out.println(e);
			 return new ResJsonMap("000001","批量启用失败，请联系管理员！").resJsonMap();
		}
	}
	/*
	 *    批量禁用
	 */
	@RequestMapping(value = "/doPlJy.do",method = RequestMethod.POST)
	public String doPlJy(@RequestBody Map map) {
		JSONObject json = (JSONObject) JSONObject.parse(JSON.toJSONString(map));
		JSONArray arr=json.getJSONArray("brandlist");
		try {
			for (Object object : arr) {
				JSONObject arrJsons = (JSONObject) JSONObject.parse(object.toString());
				int id=arrJsons.getInteger("bId");
				brandService.setStateNotUseByBId(id);
			}
			return new ResJsonMap("000000","成功").resJsonMap();
		} catch (Exception e) {
			 return new ResJsonMap("000001","批量禁用失败，请联系管理员！").resJsonMap();
		}
	}
	/*
	 *    批量删除
	 */
	@RequestMapping(value = "/doPlSc.do",method = RequestMethod.POST)
	public String doPlSc(@RequestBody Map map) {
		JSONObject json = (JSONObject) JSONObject.parse(JSON.toJSONString(map));
		JSONArray arr=json.getJSONArray("brandlist");
		try {
			for (Object object : arr) {
				JSONObject arrJsons = (JSONObject) JSONObject.parse(object.toString());
				int id=arrJsons.getInteger("bId");
				brandService.deleteByBId(id);
			}
			return new ResJsonMap("000000","成功").resJsonMap();
		} catch (Exception e) {
			 return new ResJsonMap("000001","批量删除失败，请联系管理员！").resJsonMap();
		}
	}
		
	 /**
     * 文件上传
     * @param picture
     * @param request
     * @return
     */
    @RequestMapping("/imgUpload")
    public FileResult upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
    	String userDir = System.getProperties().getProperty("user.dir");
    	System.out.println("userDir="+userDir);
        System.out.println("您已进入图片上传服务");
        //获取文件在服务器的储存位置
        String path = userDir;
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        String fileName = UUIDUtil.getUUID()+name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        System.out.println("图片地址："+path+"/"+fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return new FileResult(true,fileName,path+"/"+fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new FileResult(false, "上传失败","");
        }
    }
    
    
    @RequestMapping("/viewPhoto/{photopath}")
    public void getFeedBackPicture(HttpServletResponse response, @PathVariable("photopath")String photopath) throws Exception{
    	String userDir = System.getProperties().getProperty("user.dir");
    	System.out.println("userDir="+userDir);
    	String realPath=userDir+"/"+photopath+".png";
    	System.out.println(realPath);
        File f = new File(realPath);
        if(!f.exists()) {
        	realPath=userDir+"/"+photopath+".jpg";
        }
        f = new File(realPath);
        if(!f.exists()) {
        	realPath=userDir+"/"+photopath+".jpeg";
        }
        FileInputStream inputStream = new FileInputStream(realPath);
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }
}