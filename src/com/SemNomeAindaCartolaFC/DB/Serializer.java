package com.SemNomeAindaCartolaFC.DB;

import java.io.*;

public class Serializer {

    public static byte[] serialize(Object obj) throws IOException {

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(obj);
                return baos.toByteArray();
            }
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {

        try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {

            try(ObjectInputStream ois = new ObjectInputStream(bais)) {

                return ois.readObject();
            }
        }
    }
}
