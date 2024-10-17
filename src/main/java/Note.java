public class Note {
    private byte duration;
    private byte signal;
    private byte velocity;

    public Note(String note, byte fullNoteDuration, byte velocity) {
        char noteName = 0;
        boolean sharp = false;
        int octave = 2;
        this.velocity = velocity;
        for(char c : note.toCharArray()) {
            if(c >= 'A' && c <= 'Z') {
                noteName = c;
            }
            else if(c == '\'') {
                octave += 1;
            }
            else if(c == ',') {
                octave -= 1;
            }
            else if(c == '#') {
                sharp = true;
            }
            else if(c >= '1' && c <= '8') {
                duration = (byte)(fullNoteDuration/Integer.parseInt(new String(new char[] {c})));
            }
        }
        signal = makeNoteSignal(noteName, octave, sharp);
    }

    private byte makeNoteSignal(char note, int octave, boolean sharp) {
        octave += 1; // offset starts at octave -1
        byte offset = 0;
        if(note == 'C') {
            offset = 0;
        }
        else if(note == 'D') {
            offset = 2;
        }
        else if(note == 'E') {
            offset = 4;
        }
        else if(note == 'F') {
            offset = 5;
        }
        else if(note == 'G') {
            offset = 7;
        }
        else if(note == 'A') {
            offset = 9;
        }
        else if(note == 'B') {
            offset = 11;
        }
        else {
            return 0;
        }
        if(sharp) {
            offset += 1;
        }
        int res = (offset + (12*octave));
        if(res > 127) {
            return 0;
        }
        return (byte)res;
    }

    public byte[] getNoteEvents() {
        return new byte[] {0, (byte)0b10010000, signal, velocity, duration, (byte)0b10000000, signal, velocity};
    }
}
