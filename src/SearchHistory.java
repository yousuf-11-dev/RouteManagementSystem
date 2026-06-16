// SearchHistory.java
// This class keeps track of all searches the user has done
// We use a Stack here because stack gives us last-in-first-out behavior
// So when user wants to see recent searches they come first

import java.util.Stack;

public class SearchHistory {

    // each search entry is stored as a simple string
    Stack<String> historyStack;

    // keep a max limit so stack doesnt grow too big
    int maxHistorySize = 20;

    public SearchHistory() {
        historyStack = new Stack<>();
    }

    // add a new search to history
    public void addSearch(String fromCity, String toCity, String searchType) {
        String entry = "Search: " + fromCity + " --> " + toCity + " [" + searchType + "]";

        // if stack is full remove the oldest entry (bottom of stack)
        if (historyStack.size() >= maxHistorySize) {
            // to remove bottom element we need to temporarily store rest
            Stack<String> tempStack = new Stack<>();
            while (historyStack.size() > 1) {
                tempStack.push(historyStack.pop());
            }
            historyStack.pop(); // remove oldest
            while (!tempStack.isEmpty()) {
                historyStack.push(tempStack.pop());
            }
        }

        historyStack.push(entry);
    }

    // show all search history - most recent first (top of stack)
    public void displayHistory() {
        System.out.println("\n--- Search History (Most Recent First) ---");

        if (historyStack.isEmpty()) {
            System.out.println("No searches done yet.");
            return;
        }

        // copy stack so we dont lose data while printing
        Stack<String> tempCopy = (Stack<String>) historyStack.clone();

        int count = 1;
        while (!tempCopy.isEmpty()) {
            System.out.println(count + ". " + tempCopy.pop());
            count++;
        }
    }

    // undo last search (removes from stack)
    public String undoLastSearch() {
        if (historyStack.isEmpty()) {
            System.out.println("No search history to undo.");
            return null;
        }

        String last = historyStack.pop();
        System.out.println("Removed from history: " + last);
        return last;
    }

    // peek at last search without removing
    public String getLastSearch() {
        if (historyStack.isEmpty()) {
            return null;
        }
        return historyStack.peek();
    }

    // clear all history
    public void clearHistory() {
        historyStack.clear();
        System.out.println("Search history cleared.");
    }

    // get total searches done
    public int getTotalSearches() {
        return historyStack.size();
    }
}
