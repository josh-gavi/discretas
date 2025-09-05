/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffprojc.Modelo;

/**
 *
 * @author Kailo
 */
import java.io.*;
import java.util.*;

public class HuffmanCompressor {

    // ================== COMPRESIÓN ==================
    public static void compressFile(File input, File output) throws IOException {
        
        byte[] data = java.nio.file.Files.readAllBytes(input.toPath());

        
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append((char) (b & 0xFF)); 
        }
        String text = sb.toString();

        
        HuffmanTree tree = new HuffmanTree(text);
        Map<Character, String> codes = tree.getCodes();

        
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }

        
        BitSet bitset = new BitSet(encoded.length());
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '1') {
                bitset.set(i);
            }
        }

        
        String ext = getFileExtension(input);
        String originalName = input.getName();

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output))) {
            oos.writeObject(codes);          // códigos de Huffman
            oos.writeInt(encoded.length());  // longitud real de bits
            oos.writeObject(bitset);         // datos comprimidos
            oos.writeUTF(ext);               // extensión original
            oos.writeUTF(originalName);      // nombre original
        }
    }

    // ================== DESCOMPRESIÓN ==================
    @SuppressWarnings("unchecked")
    public static File decompressFile(File input, File outputDir) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(input))) {
            Map<Character, String> codes = (Map<Character, String>) ois.readObject();
            int bitLength = ois.readInt();
            BitSet bitset = (BitSet) ois.readObject();
            String ext = ois.readUTF();
            String originalName = ois.readUTF();

            
            Map<String, Character> reverseCodes = new HashMap<>();
            for (var e : codes.entrySet()) {
                reverseCodes.put(e.getValue(), e.getKey());
            }

            
            StringBuilder bits = new StringBuilder();
            for (int i = 0; i < bitLength; i++) {
                bits.append(bitset.get(i) ? '1' : '0');
            }

            
            StringBuilder decoded = new StringBuilder();
            String currentCode = "";
            for (int i = 0; i < bits.length(); i++) {
                currentCode += bits.charAt(i);
                if (reverseCodes.containsKey(currentCode)) {
                    decoded.append(reverseCodes.get(currentCode));
                    currentCode = "";
                }
            }

            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i = 0; i < decoded.length(); i++) {
                baos.write((byte) decoded.charAt(i));
            }

            
            File restored = new File(outputDir, originalName);
            try (FileOutputStream fos = new FileOutputStream(restored)) {
                fos.write(baos.toByteArray());
            }

            System.out.println("Archivo restaurado en: " + restored.getAbsolutePath());
            return restored;
        }
    }

    // ================== AUXILIARES ==================
    private static String getFileExtension(File file) {
        String name = file.getName();
        int i = name.lastIndexOf('.');
        return (i > 0) ? name.substring(i + 1) : "";
    }
}