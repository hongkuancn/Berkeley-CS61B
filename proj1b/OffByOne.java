public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        /* Requirement about uppercase is not very clear.
        https://sp18.datastructur.es/materials/proj/proj1b/proj1b  */

        if (x -y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }


}
