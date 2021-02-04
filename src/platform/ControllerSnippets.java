package platform;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerSnippets {
    private final ArrayList<CodeModel> snippets;

    public ControllerSnippets() {
        snippets = new ArrayList<>();
    }

    public CodeModel getLatestSnippet() {
        return snippets.get(snippets.size() - 1);
    }

    public CodeModel getSnippetAt(int index) {
        return snippets.get(index);
    }

    public void addSnippet(CodeModel code) {
        snippets.add(code);
    }

    public int getSize() {
        return snippets.size();
    }

    public List<CodeModel> getLatestSnippets() {
        List<CodeModel> result = new ArrayList<>();

        for (int i = 1; i <= 10 && snippets.size() - i >= 0; i++)
            result.add(snippets.get(snippets.size() - i));

        return result;
    }
}
