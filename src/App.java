import java.util.Arrays;

public class App {
    
    static char[][] maze = {
        {'#', 's', '#', '#', '#'},
        {'#', '.', '.', '.', '#'},
        {'.', '.', '#', '.', '#'},
        {'.', '#', '.', '.', '#'},
        {'#', '#', '#', '.', 'f'}
    };

    static char[][] partInMaze = new char[maze.length][maze[0].length];
    
    public static void main(String[] args) throws Exception {
        int startLine = 0;
        int startColumn = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 's') {
                    startLine = i;
                    startColumn = j;
                    break;
                }
            }
        }
        installationWalls(0, 0);

        if (findPath(startLine, startColumn)) {
            System.out.println("Путь найден:");
        } else {
            System.out.println("Путь не найден!");
        }

        for (int i = 0; i < partInMaze.length; i++) {
            for (int j = 0; j < partInMaze[i].length; j++) {
                char current = partInMaze[i][j];
                if (current == '\u0000') {
                    System.out.print('.');
                } else {
                    System.out.print(current);
                }
            }
        System.out.println();
        }
        
    }
    static boolean findPath(int line, int coulumn){
        
        if (line < 0 || line > maze.length - 1 || coulumn < 0 || coulumn > maze[0].length - 1) return false;

        if (maze[line][coulumn] == 'f') {
            partInMaze[line][coulumn] = 'f';
            return true;
        }

        if (partInMaze[line][coulumn] == '*') {
            return false;
        } 

        if (maze[line][coulumn] == 's') {
            partInMaze[line][coulumn] = 's';
        } 
        else 
        if (maze[line][coulumn] == '.') {
            partInMaze[line][coulumn] = '*';
        }

        if (partInMaze[line][coulumn] == '#') {
            return false;
        }  

        if (findPath(line + 1, coulumn)) return true;
        if (findPath(line - 1, coulumn)) return true;
        if (findPath(line, coulumn + 1)) return true;
        if (findPath(line, coulumn - 1)) return true;

        if (maze[line][coulumn] != 's') {
            partInMaze[line][coulumn] = '.'; // пустой символ
        }

        return false;
    }
    static void installationWalls(int i, int j){
        if (j > maze[0].length - 1){
            return;
        }

        if (i > maze.length - 1){
            installationWalls (0, j + 1);
            return;
        }

        if (maze[i][j] == '#'){
            partInMaze[i][j] = '#';
        }
        installationWalls (i + 1, j);
    }
}
