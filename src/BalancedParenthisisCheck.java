import java.util.*;

// Determine whether given expression is balanced parenthesis expression or not.
//Main Assumption is that we always try to balance the expression.


    public class BalancedParenthisisCheck {
        // Function to check if two brackets are matching or not.

        public static int isMatching(char a,
                       char b) {
            if ( (a == '[' && b == ']') ||  a == '$') {
                return 1;
            }
            return 0;
        }

        // Recursive function to check if given expression is balanced or not.

        public static int isBalanced(String s,
                       Stack<Character> ele,
                       int ind) {

            // Base case.
            // If the string is balanced then all the opening brackets had been popped and stack
            // should be empty after string is traversed completely.
            if (ind == s.length()) {
                if (ele.size() == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }

            // variable to store element at the top of the stack.
            char topEle;

            // variable to store result of recursive call.
            int res;

            // Scenario 1: When current element is an opening bracket then push that element in the stack.
            if (s.charAt(ind) == '[') {
                ele.push(s.charAt(ind));
                return isBalanced(s, ele, ind + 1);
            } // Scenario 2: When current element is a closing bracket then check for matching bracket at the top of the stack.
            else if (s.charAt(ind) == ']') {

                // If stack is empty then there is no matching opening bracket
                // for current closing bracket and the expression is not balanced.
                if (ele.size() == 0) {
                    return 0;
                }

                topEle = ele.peek();
                ele.pop();

                // Check bracket is matching or not.
                if (isMatching(topEle, s.charAt(ind)) == 0) {
                    return 0;
                }

                return isBalanced(s, ele, ind + 1);
            } // Scenario 3: If current element is '$' then check for both the cases when '$' could be
            // opening or closing bracket.
            else if (s.charAt(ind) == '$') {
                Stack<Character> tmp = new Stack<>();

                tmp.addAll(ele);
                tmp.push(s.charAt(ind));
                res = isBalanced(s, tmp, ind + 1);
                if (res == 1) {
                    return 1;
                }
                if (ele.size() == 0) {
                    return 0;
                }
                ele.pop();
                return isBalanced(s, ele, ind + 1);
            }
            return 1;
        }


        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            Stack<Character> ele = new Stack<>();//Initial declarion of Stack

            if (isBalanced(input, ele, 0) != 0) {
                System.out.println("Balanced");
            } else {
                System.out.println("Not Balanced");
            }
        }
    }
