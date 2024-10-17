public class MIDITrack {
    private byte instrument;
    private Note[] notes;

    public MIDITrack(String[] notes, byte fullNoteDuration, byte defaultVelocity, byte instrument) {
        this.instrument = instrument;
        this.notes = new Note[notes.length];
        for(int i=0; i < notes.length; i++) {
            this.notes[i] = new Note(notes[i], fullNoteDuration, defaultVelocity);
        }
    }

    public byte[] getTrack() {
        byte[] noteSignals = new byte[notes.length*8 + 3];
        for(int i=0; i < notes.length; i++) {
            byte[] noteSignal = notes[i].getNoteEvents();
            for(int j=0; j < noteSignal.length; j++) {
                noteSignals[8 * i + j] = noteSignal[j];
            }
        }
        noteSignals[noteSignals.length-3] = (byte) 0xFF;
        noteSignals[noteSignals.length-2] = (byte) 0x2F;
        noteSignals[noteSignals.length-1] = (byte) 0x00;
        return MIDITools.joinByteArrays(getHeader(), noteSignals);
    }

    private byte[] getHeader() {
        return new byte[] {0x4D, 0x54, 0x72, 0x6B, 0x00, 0x00, 0x00, (byte)(8*notes.length + 18),
                0x00, (byte)0xFF, 0x58, 0x04, 0x04, 0x02, 0x18, 0x08, 0x00, (byte)0xFF, 0x51, 0x03, 0x07, (byte)0xA1, 0x20,
                0x00, (byte)0xC0, instrument};
    }
}
