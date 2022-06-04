package you_passed_it;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        HashMap<String, Vacancy> vacancies = readData();
        rateCandidatesPerVacancy(vacancies);
        ArrayList<Candidate> successfulCandidates = getResultList(vacancies);
        Collections.sort(successfulCandidates, new CandidateComparatorByName());

        for (Candidate successfulCandidate : successfulCandidates) {
            System.out.println(successfulCandidate);
        }
    }

    private static HashMap<String, Vacancy> readData() {
        try (Scanner in = new Scanner(new File("src/you_passed_it/input.txt"));) {
            HashMap<String, Vacancy> vacancies = new HashMap<>();

            int vC = Integer.parseInt(in.nextLine());
            for (int i = 0; i < vC; i++) {
                String[] vD = in.nextLine().split(",");
                Vacancy vacancy = new Vacancy(vD[0], Integer.parseInt(vD[1]));
                vacancies.put(vD[0], vacancy);
            }

            int cC = Integer.parseInt(in.nextLine());
            for (int i = 0; i < cC; i++) {
                String[] cD = in.nextLine().split(",");
                Candidate candidate = new Candidate(cD[0], cD[1], Integer.parseInt(cD[2]), Integer.parseInt(cD[3]));
                vacancies.get(cD[1]).addCandidate(candidate);
            }

            return vacancies;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void rateCandidatesPerVacancy(HashMap<String, Vacancy> vacancies) {
        for (Vacancy vacancy : vacancies.values()) {
            Collections.sort(vacancy.getCandidates(), new CandidateComparatorByGradeAndFine());
        }
    }

    private static ArrayList<Candidate> getResultList(HashMap<String, Vacancy> vacancies) {
        ArrayList<Candidate> successfulCandidates = new ArrayList<>();

        for (Vacancy vacancy : vacancies.values()) {
            int successfulCandidatesCount = Math.min(vacancy.getAmount(), vacancy.getCandidates().size());
            for (int i = 0; i < successfulCandidatesCount; i++) {
                successfulCandidates.add(vacancy.getCandidates().get(i));
            }
        }

        return successfulCandidates;
    }
}

class Candidate {
    private String name;
    private String vacancy;
    private int grade;
    private int fine;

    public Candidate(String name, String vacancy, int grade, int fine) {
        this.name = name;
        this.vacancy = vacancy;
        this.grade = grade;
        this.fine = fine;
    }

    public int getGrade() {
        return grade;
    }

    public int getFine() {
        return fine;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}

class CandidateComparatorByGradeAndFine implements Comparator<Candidate> {
    @Override
    public int compare(Candidate c1, Candidate c2) {
        int result = c2.getGrade() - c1.getGrade();
        if (result == 0) {
            result = c1.getFine() - c2.getFine();
        }
        return result;
    }
}

class CandidateComparatorByName implements Comparator<Candidate> {
    @Override
    public int compare(Candidate c1, Candidate c2) {
        return c1.getName().compareTo(c2.getName());
    }
}

class Vacancy {
    private String name;
    private int amount;
    private ArrayList<Candidate> candidates;

    public Vacancy(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.candidates = new ArrayList<>();
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public int getAmount() {
        return amount;
    }
}