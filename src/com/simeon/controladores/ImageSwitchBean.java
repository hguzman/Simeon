package com.simeon.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="imageSwitchBean")
@RequestScoped
public class ImageSwitchBean {
	private List<String> images;  
	  
    public ImageSwitchBean() {  
        images = new ArrayList<String>();  
        images.add("banermetro.png");  
/*        images.add("aaa.png");  
        images.add("metrotel.png");  
        images.add("curadunuevo.png");  
*/    }  
  
    public List<String> getImages() {  
        return images;  
    }  
}
