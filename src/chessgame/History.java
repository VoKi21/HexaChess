package chessgame;

import chessgame.figures.FigureRecord;

import java.util.List;

public class History {
    private static class HistoryEntry {
        List<FigureRecord> situation;
        HistoryEntry previous;
        HistoryEntry next;
        HistoryEntry(List<FigureRecord> situation) {
            this.situation = situation;
            this.next = null;
            this.previous = null;
        }
    }

    private HistoryEntry latest;
    private HistoryEntry current;

    public void add(List<FigureRecord> situation) {
        HistoryEntry newEntry = new HistoryEntry(situation);
        if (latest == null) {
            latest = newEntry;
            current = latest;
        } else {
            latest.next = newEntry;
            newEntry.previous = latest;
            latest = latest.next;
        }
    }

    public boolean prev() {
        if (current.previous != null) {
            current = current.previous;
            return true;
        }
        return false;
    }

    public boolean next() {
        if (current != latest) {
            current = current.next;
            return true;
        } else {
            return false;
        }
    }

    public List<FigureRecord> getCurrentSituation() {
        return current.situation;
    }

    public boolean inTheEnd() {
        return current == latest;
    }
}
