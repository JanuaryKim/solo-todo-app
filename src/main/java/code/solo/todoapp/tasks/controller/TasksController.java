package code.solo.todoapp.tasks.controller;


import code.solo.todoapp.tasks.dto.TasksDto;
import code.solo.todoapp.tasks.entity.Tasks;
import code.solo.todoapp.tasks.mapper.TasksMapper;
import code.solo.todoapp.tasks.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;


@CrossOrigin(origins = "https://todobackend.com")
@Validated
@RequiredArgsConstructor
@RestController
public class TasksController {

    private final TasksService tasksService;
    private final TasksMapper tasksMapper;

    @PostMapping("/")
    public ResponseEntity postTasks(@RequestBody @Valid TasksDto.Post dto) {
        Tasks tasks = tasksMapper.tasksDtoPostToTasks(dto);
        Tasks createTasks = tasksService.createTasks(tasks);
        TasksDto.Response tasksDtoResponse = tasksMapper.tasksToTasksDtoResponse(createTasks);
        return new ResponseEntity(tasksDtoResponse, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity patchTasks(@Positive @PathVariable("id") Long id, @RequestBody @Valid TasksDto.Patch dto) {
        Tasks tasks = tasksMapper.tasksDtoPatchToTasks(dto);
        tasks.setId(id);
        Tasks updatedTasks = tasksService.updateTasks(tasks);
        TasksDto.Response tasksDtoResponse = tasksMapper.tasksToTasksDtoResponse(updatedTasks);
        return new ResponseEntity(tasksDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTasks(@PathVariable("id") @Positive Long id) {

        Tasks tasks = tasksService.findTasks(id);
        TasksDto.Response tasksDtoResponse = tasksMapper.tasksToTasksDtoResponse(tasks);
        return new ResponseEntity(tasksDtoResponse, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity getTasksList() {

        List<Tasks> tasksList = tasksService.findTasksList();
        List<TasksDto.Response> tasksDtoResponseList = tasksMapper.tasksListToTasksDtoResponseList(tasksList);
        return new ResponseEntity(tasksDtoResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTasks(@PathVariable("id") @Positive Long id) {

        tasksService.eraseTasks(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteAllTasks() {

        tasksService.eraseAllTasks();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
