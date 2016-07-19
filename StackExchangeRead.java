import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.Scanner;

public class StackExchangeRead {

    public static void main(String []args) {
        String url;
        Element question;
        Element questiontext;
        Element asker;
        Elements commentsonq;
        Elements answers;
        Element answerer;
        Element reply;
        Elements comments;
        Element commentmaker;
        Element commenttext;
        Elements images;
        String imgsrc;
        String imgname;
        int indexname;
        try {

            //url="http://electronics.stackexchange.com/questions/15135/decoupling-caps-pcb-layout/15143";

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter URL: ");
            url = sc.next();

            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:47.0) Gecko/20100101 Firefox/47.0").get();
            System.out.println("Connection established");
            String title = doc.title();

            WriteToHTMLFile hf = new WriteToHTMLFile(title.replace("/"," ")+".html");
            System.out.println("File opened");
            DownloadImages di = new DownloadImages();

            hf.clearFile();
            hf.insertLine("<!DOCTYPE html>\n<html>\n<head>");
            hf.insertLine("<title>"+title+"</title>");
            hf.insertLine("<meta author=\"stackexchange.com\">\n</head>\n<body>");
            hf.insertLine("<h1>"+title+"</h1>");

            question = doc.getElementsByClass("question").first();
            questiontext = question.getElementsByClass("post-text").first();

            images = questiontext.getElementsByTag("img");
            for (Element image : images) {
                imgsrc = image.absUrl("src");
                di.getImages(imgsrc);
                imgname = imgsrc.substring(imgsrc.lastIndexOf("/") + 1);
                image.attr("src",imgname);
                image.attr("style","max-width:100%");
            }

            asker = question.getElementsByClass("user-details").last();
            hf.insertLine("<h3><u>Question</u></h3>");
            if (asker.getElementsByTag("a").isEmpty()) {
                hf.insertLine("<p><b>"+asker.text()+"</b></p>");
            }
            else {
                hf.insertLine("<p><b>"+asker.getElementsByTag("a").first().text()+"</b></p>");
            }
            hf.insertLine("<p>"+questiontext.toString()+"</p>");
            hf.insertLine("<blockquote>");
            commentsonq = question.getElementsByClass("comment-body");
            for (Element comment : commentsonq) {
                commentmaker = comment.getElementsByClass("comment-user").first();
                hf.insertLine("<p><b>"+commentmaker.text()+"</b></p>");
                commenttext = comment.getElementsByClass("comment-copy").first();
                hf.insertLine("<p>"+commenttext.toString()+"</p>");
            }
            hf.insertLine("</blockquote>");

            answers = doc.getElementsByClass("answer");
            for (Element answer : answers) {
                reply = answer.getElementsByClass("post-text").first();

                images = reply.getElementsByTag("img");
                for (Element image : images) {
                    imgsrc = image.absUrl("src");
                    di.getImages(imgsrc);
                    imgname = imgsrc.substring(imgsrc.lastIndexOf("/") + 1);
                    image.attr("src",imgname);
                    image.attr("style","max-width:100%");
                }

                answerer = answer.getElementsByClass("user-details").last();
                hf.insertLine("<h3><u>Answer</u></h3>");
                if (answerer.getElementsByTag("a").isEmpty()) {
                    hf.insertLine("<p><b>"+answerer.text()+"</b></p>");
                }
                else {
                    hf.insertLine("<p><b>"+answerer.getElementsByTag("a").first().text()+"</b></p>");
                }
                hf.insertLine("<p>"+reply.toString()+"</p>");
                comments = answer.getElementsByClass("comment-body");
                hf.insertLine("<blockquote>");
                for (Element comment : comments) {
                    commentmaker = comment.getElementsByClass("comment-user").first();
                    hf.insertLine("<p><b>"+commentmaker.text()+"</b></p>");
                    commenttext = comment.getElementsByClass("comment-copy").first();
                    hf.insertLine("<p>"+commenttext.text()+"</p>");
                }
                hf.insertLine("</blockquote>");
            }
            System.out.println("Conversion successful");
        }
        catch (IOException e) {
            System.out.println("Oops! Some files are missing.");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
            System.out.println("Oops! Couldn't parse the URL. Waiting for a fix.");
        }
    }
} 
