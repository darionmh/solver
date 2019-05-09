/**
 * Created by Darion Higgins on 5/9/2019
 * TSO2438
 */
class Main2by2 {

    static void main(String[] args) {
        int max = 4

        List<Expression> expressions = setupExpressions()

        expressions.forEach({
            println it.toString()
            it.setCases(deriveCases(it, max))
            println ""
        })

        List<Point> points = flattenExpressions(expressions)
        Map<String, List<Point>> groupedPoints = groupPoints(points)
        println createIntersections(groupedPoints)
    }

    static List<Expression> setupExpressions() {
        List cases = []

        cases.add(new Expression(new Point(0, 0), new Point(0, 1), Operator.ADD, 4)) //TOP
        cases.add(new Expression(new Point(1, 0), new Point(1, 1), Operator.ADD, 6)) //Bottom

        cases.add(new Expression(new Point(0, 0), new Point(1, 0), Operator.ADD, 3)) //LEFT
        cases.add(new Expression(new Point(0, 1), new Point(1, 1), Operator.ADD, 7)) //Right

        return cases
    }

    static Map<String, List<Point>> groupPoints(List<Point> points){
        Map<String, List<Point>> groups = new HashMap<String, List<Point>>()
        points.forEach({
            if(!groups.containsKey(it.coords())){
                groups.put(it.coords(), [])
            }

            groups.get(it.coords()).add(it)
        })

        return groups
    }

    static List<Point> createIntersections(Map<String, List<Point>> groups){
        List<Point> intersected = []

        groups.keySet().forEach({
            List<Point> points = groups.get(it)
            Point intersect = new Point(it)
            intersect.setValues(intersectLists(points.get(0).values, points.get(1).values))
            intersected.add(intersect)
        })

        return intersected
    }

    static List<Point> flattenExpressions(List<Expression> expressions) {
        return expressions.stream().map({ [it.a, it.b] as List<Point> }).flatMap({it.stream()}).collect()
    }



    static List<Integer> intersectLists(List<Integer> a, List<Integer> b) {
        return a.intersect(b)
    }

    static List<Case> deriveCases(Expression expression, int max) {
        List<Case> cases = []

        for (int a = 1; a <= max; a++) {
            for (int b = 1; b <= max; b++) {
                if (a == b) continue;

                if (result(a, b, expression.operator) == expression.result) {
                    expression.a.values.add(a)
                    expression.b.values.add(b)
                    println String.format("%d %s %d = %d", a, expression.operator.symbol, b, expression.result)
                }
            }
        }

        return cases
    }

    static double result(int a, int b, Operator op) {
        switch (op) {
            case Operator.ADD: return a + b
            case Operator.DIVIDE: return a / b
            case Operator.MULTIPLY: return a * b
            case Operator.SUBTRACT: return a - b
            default: return 0
        }
    }

    static class Expression {
        Point a
        Point b
        Operator operator
        int result
        List<Case> cases

        Expression(Point a, Point b, Operator operator, int result) {
            this.a = a
            this.b = b
            this.operator = operator
            this.result = result
            cases = []
        }

        @Override
        String toString() {
            String.format("? %s ? = %d", operator.symbol, result)
        }
    }

    static class Case {
        int a
        int b

        Case(int a, int b) {
            this.a = a
            this.b = b
        }
    }

    static class Point {
        int row
        int col
        List<Integer> values = []

        Point(int row, int col) {
            this.row = row
            this.col = col
        }

        Point(String coords){
            this.row = Integer.parseInt(coords.split(" ")[0])
            this.col = Integer.parseInt(coords.split(" ")[1])
        }

        @Override
        String toString() {
            return String.format("{%d, %d} => %s", row, col, values.toListString())
        }

        String coords(){
            return String.format("%d %d", row, col)
        }
    }

    static enum Operator {
        ADD("+"), SUBTRACT("-"), MULTIPLY("x"), DIVIDE("/");

        String symbol

        Operator(String symbol) {
            this.symbol = symbol
        }
    }
}
