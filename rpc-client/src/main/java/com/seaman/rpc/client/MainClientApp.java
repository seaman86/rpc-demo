package com.seaman.rpc.client;

import com.seaman.rpc.api.IProductService;
import com.seaman.rpc.api.bean.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * 版权：    上海云砺信息科技有限公司
 * 创建者:   wangqiuhua
 * 创建时间:  2019-01-22 13:48
 * 功能描述:
 * 修改历史:
 */
public class MainClientApp {

    public static void main(String[] args) {
        IProductService productService = (IProductService) rpc(IProductService.class);
        Product product = productService.getById(100L);

        System.out.println(product);
    }

    //注解说明测试一下
    private static Object rpc(final Class clazz) {
        return newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1", 8888);

                        String apiClassName = clazz.getName();
                        String methodName = method.getName();
                        Class[] parameterTypes = method.getParameterTypes();

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(apiClassName);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);
                        objectOutputStream.flush();


                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object o = objectInputStream.readObject();
                            objectInputStream.close();
                            objectOutputStream.close();

                            socket.close();
                            return o;
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }
        );

    }
}
