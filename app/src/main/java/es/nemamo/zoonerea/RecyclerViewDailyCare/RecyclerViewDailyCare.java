package es.nemamo.zoonerea.RecyclerViewDailyCare;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.DailyCare;
import es.nemamo.zoonerea.Model.Task;
import es.nemamo.zoonerea.Model.TaskType;

public class RecyclerViewDailyCare {
    private DailyCare dailyCare;
    private Task task;
    private TaskType taskType;
    private Animal animal;
    private int count;

    public RecyclerViewDailyCare(DailyCare dailyCare, Task task, TaskType taskType, Animal animal) {
        this.dailyCare = dailyCare;
        this.task = task;
        this.taskType = taskType;
        this.animal = animal;

        if (taskType.getId()==1)
            count = animal.getFrequency_al();
        else
            count = 1;
    }

    public DailyCare getDiaryCare() {
        return dailyCare;
    }

    public Task getTask() {
        return task;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getCount() {
        return count;
    }
}
