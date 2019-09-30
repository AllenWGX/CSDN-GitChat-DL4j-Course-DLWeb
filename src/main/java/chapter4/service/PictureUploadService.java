package chapter4.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


@Service
public class PictureUploadService implements InitializingBean{
    private static final Logger LOG = LoggerFactory.getLogger(PictureUploadService.class);
    private MultiLayerNetwork model;
    private NativeImageLoader imageloader;
    private Map<Integer,String> map;

    @Override
    public void afterPropertiesSet() throws Exception {
        //加载训练好的模型
        model = ModelSerializer.restoreMultiLayerNetwork("fashionmnist/model.mod");
        //声明图片加载器
        imageloader = new NativeImageLoader(28, 28, 1);
        //声明分类标签的映射关系
        map = new HashMap<Integer,String>(){{put(0,"T-shirt");put(1,"Trouser");
                                            put(2,"Pullover");put(3,"Dress");
                                            put(4,"Coat");put(5,"Sandal");
                                            put(6,"Shirt");put(7,"Sneaker");
                                            put(8,"Bag");put(9,"Ankle boot");}};
        LOG.info("Finish Loading Model");
    }
    
    /***
     *  图片分类的主逻辑 
     */
    public String fashionReco(File pic) throws IOException{
        INDArray feature = imageloader.asRowVector(pic);
        feature = feature.div(255.0).rsub(1.0); //归一化
        int label = model.predict(feature)[0];
        return map.get(label);
    }
}
