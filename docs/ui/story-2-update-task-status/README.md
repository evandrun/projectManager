# Story 2 - Update Task Status

User story (FR-4): As a team member, I want to update the status of my tasks so that I can stay on track.

Acceptance criterion: Given a task assigned to me, when I change its status to Completed, then my manager sees the task as Completed.

Designer: Diego Chavez Espinosa
Tester: Ethan Van Drunen

Figma file: https://www.figma.com/design/LD9dTmIjsYaAcNou4hdGIc
Prototype: https://www.figma.com/proto/LD9dTmIjsYaAcNou4hdGIc?node-id=2-2&starting-point-node-id=2%3A2

## Lo-Fi

lofi/lofi-flow.jpg - one sketch with all 3 screens and arrows:
- My Tasks - shows each project's progress bar and its tasks. My tasks have a "Mine" tag.
- Task - shows the project and description. Tapping a status saves it and highlights it.
- Not your task - error screen when you open a task that belongs to someone else.

## Hi-Fi

- screen-1-my-tasks.png - main screen with Website Redesign at 50% and Launch at 0%. My task is outlined in blue.
- screen-2-change-status.png - Task 2 with In Progress highlighted. Tap a new status to change it.
- screen-3-status-saved.png - Completed is highlighted and a "Status updated" message shows up.
- screen-4-my-tasks-updated.png - back on the main screen, Task 2 is Completed and Website Redesign is at 100%.
- screen-5-not-your-task.png - error screen for a task assigned to another team member, so the status can't be changed.

## How to click through the prototype

1. On My Tasks, tap Task 2 under Website Redesign.
2. Tap Completed.
3. Tap "My Tasks" at the top to go back and see the update.
4. To see the error, tap Task 2 under Launch.
