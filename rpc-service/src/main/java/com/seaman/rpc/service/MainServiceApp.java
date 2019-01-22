package com.seaman.rpc.service;

import com.seaman.rpc.api.IProductService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 版权：    上海云砺信息科技有限公司
 * 创建者:   wangqiuhua
 * 创建时间:  2019-01-22 14:29
 * 功能描述:
 * 修改历史:
 */

public class MainServiceApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                String apiClassName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                Object[] args4Method = (Object[]) objectInputStream.readObject();

                Class clazz = null;

                if(apiClassName.equals(IProductService.class.getName())){
                    clazz = ProductService.class;
                }

                Method method = clazz.getMethod(methodName,parameterTypes);
                Object invoke = method.invoke(clazz.newInstance(),args4Method);

                ObjectOutputStream objectOutputStream =new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(invoke);
                objectOutputStream.flush();

                objectOutputStream.close();
                objectInputStream.close();
                socket.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
