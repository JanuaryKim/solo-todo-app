package code.solo.todoapp.tasks.repository;

import code.solo.todoapp.tasks.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
