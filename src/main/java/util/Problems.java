package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problems {
    public Set<String> getACProblems(String userName) {
        Set<String> result = new HashSet<String>();
        Document doc = null;
        try {
            String url = "http://acm.hdu.edu.cn/userstatus.php?user=" + userName;
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.select("p[align=left]").select("script[language=javascript]");
        String problems = elements.first().data();
        Pattern patten = Pattern.compile("p\\(([\\d]*),([\\d]*),([\\d]*)\\);");
        Matcher matcher = patten.matcher(problems);
        while(matcher.find()) {
            String problem = matcher.group(1);
            System.out.println(problem);
            result.add(problem);
        }
        return result;
    }
}
