public class MIDITools {
    public static byte[] joinByteArrays(byte[] array1, byte[] array2) {
        byte[] res = new byte[array1.length + array2.length];
        int pos = 0;
        for(int i=0; i<array1.length; i++) {
            res[pos] = array1[i];
            pos++;
        }
        for(int i=0; i<array2.length; i++) {
            res[pos] = array2[i];
            pos++;
        }
        return res;
    }
}
