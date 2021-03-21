package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnpackingStrings {

    private static Pattern RegexMatcherSequenceBracketsPattern =
            Pattern.compile("\\d+\\[[a-z0-9]+\\]", Pattern.CASE_INSENSITIVE);

    private static Pattern RegexFindNumberPattern =
            Pattern.compile("(\\d+)\\[", Pattern.CASE_INSENSITIVE);

    private static Pattern RegexFindStringIntoBracketsPattern =
            Pattern.compile("\\[([a-z0-9]+)\\]", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_string = scanner.nextLine();
        if(input_string.matches("([a-zA-Z]*)\\d+\\[[a-zA-Z0-9\\[\\]]+\\]([a-zA-Z]*)")) {
            System.out.println(getExpandStringByRegex(input_string));
        }
        else {
            System.out.println("The string is not valid");
        }
    }

    private static String getExpandStringByRegex(String input) {

        Matcher matcher = RegexMatcherSequenceBracketsPattern.matcher(input);
        String[] matches;
        String result = input;

        while (matcher.find()) {
            matches = matcher.group().split("\n");

            for (int i = 0; i < matches.length; i++) {
                result = result.replace(matches[i], ApplyMultiplyForMatches(matches[i]));
            }

            return getExpandStringByRegex(result);
        }
        return result;

    }

    private static String ApplyMultiplyForMatches(String match) {

        Matcher matcherNumber = RegexFindNumberPattern.matcher(match);
        Matcher matcherTextIntoBrackets = RegexFindStringIntoBracketsPattern.matcher(match);

        int count = 0;
        String textIntoBrackets = "";

        if (matcherNumber.find()) {
            count = Integer.parseInt(matcherNumber.group(1));
        }

        if (matcherTextIntoBrackets.find()) {
            textIntoBrackets = matcherTextIntoBrackets.group(1);
        }

        return MultiplyString(textIntoBrackets, count);
    }

    private static String MultiplyString(String str, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(str);
        }
        return result.toString();
    }
}