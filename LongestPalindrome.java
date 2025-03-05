import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome {
    // Function to find all longest palindromic substrings
    public static Set<String> longestPalindromes(String s) {
        if (s == null || s.length() < 1) return new HashSet<>();

        int maxLength = 0;
        Set<String> longestPalindromes = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            // Expanding from a single character (odd-length palindrome)
            String palindrome1 = expandAroundCenter(s, i, i);
            // Expanding from two characters (even-length palindrome)
            String palindrome2 = expandAroundCenter(s, i, i + 1);

            // Store the longest palindromes found
            maxLength = updateLongestPalindromes(longestPalindromes, palindrome1, maxLength);
            maxLength = updateLongestPalindromes(longestPalindromes, palindrome2, maxLength);
        }

        return longestPalindromes;
    }

    // Helper function to expand around the center and return the longest palindrome
    private static String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    // Helper function to update the set of longest palindromes
    private static int updateLongestPalindromes(Set<String> palindromes, String candidate, int maxLength) {
        if (candidate.length() > maxLength) {
            palindromes.clear();
            palindromes.add(candidate);
            return candidate.length();
        } else if (candidate.length() == maxLength) {
            palindromes.add(candidate);
        }
        return maxLength;
    }

    // Helper function to format output as "bab" or "aba"
    private static String formatOutput(Set<String> palindromes) {
        return String.join(" or ", palindromes);
    }

    // Main method to test the function
    public static void main(String[] args) {
        String input1 = "babad";
        String input2 = "cbbd";
        String input3 = "a";
        String input4 = "abacdfgdcaba";

        System.out.println("Input: " + input1 + " -> Output: " + formatOutput(longestPalindromes(input1)));
        System.out.println("Input: " + input2 + " -> Output: " + formatOutput(longestPalindromes(input2)));
        System.out.println("Input: " + input3 + " -> Output: " + formatOutput(longestPalindromes(input3)));
        System.out.println("Input: " + input4 + " -> Output: " + formatOutput(longestPalindromes(input4)));
    }
}
