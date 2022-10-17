package code.solo.todoapp.tasks.mapper;

import code.solo.todoapp.tasks.dto.TasksDto;
import code.solo.todoapp.tasks.entity.Tasks;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TasksMapper {

    Tasks tasksDtoPostToTasks(TasksDto.Post dto);

    Tasks tasksDtoPatchToTasks(TasksDto.Patch dto);

    TasksDto.Response tasksToTasksDtoResponse(Tasks tasks);

    default List<TasksDto.Response> tasksListToTasksDtoResponseList(List<Tasks> list) {

        List<TasksDto.Response> responseList = list.stream().map(tasks -> {
            TasksDto.Response responseDto = new TasksDto.Response();
            responseDto.setId(tasks.getId());
            responseDto.setTitle(tasks.getTitle());
            responseDto.setOrder(tasks.getOrder());
            responseDto.setCompleted(tasks.getCompleted());
            return responseDto;
        }).collect(Collectors.toList());

        return  responseList;
    }
}
