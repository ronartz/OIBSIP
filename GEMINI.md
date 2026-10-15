# Git Commits & Version Control Rules

These instructions apply to all work and commits in this repository.

## 1. Natural, Human-Readable Commit Messages

Write commit messages in clear, simple, everyday English.

Messages should sound like a developer naturally describing what changed.

**Preferred examples:**
- `Add user login page`
- `Fix navigation between screens`
- `Update dashboard layout`
- `Improve mobile responsiveness`
- `Fix incorrect progress calculation`
- `Clean up unused components`
- `Add input validation`
- `Update project documentation`
- `Fix database connection issue`
- `Improve loading and error messages`

**Avoid unnecessarily complicated messages such as:**
- `feat(core): implement V12 architectural enhancements`
- `fix(ops): harden environment orchestration pipeline`
- `refactor(ui): optimize component abstraction hierarchy`
- `chore(infra): synchronize runtime dependency configuration`

Use technical terminology when it accurately describes a change, but never add jargon just to make a commit sound sophisticated.

## 2. Commit Message Format

Follow these principles:

1. Use short, descriptive messages.
2. Begin with a clear action word such as Add, Fix, Update, Remove, Improve, Refactor, or Simplify.
3. Explain what actually changed.
4. Prefer one concise sentence.
5. Avoid emojis, excessive capitalization, and promotional language.
6. Avoid unnecessary prefixes such as `feat:`, `fix:`, `chore:`, or `refactor:`.
7. Don't mention AI tools, model names, or agents in routine commit messages unless that information is genuinely relevant.
8. Never invent changes or use misleading descriptions.
9. Use Conventional Commits only when the project's established conventions or automation require them.

## 3. Meaningful Commit History

Keep the commit history clean, logical, and easy to understand:

- Group closely related changes into one commit.
- Keep unrelated changes in separate commits.
- Avoid creating numerous tiny commits for one simple change.
- Do not repeatedly commit the same message for different work.
- Do not create empty or meaningless commits.
- Do not manufacture or backdate commits to make the history appear different.
- Do not combine major unrelated features into one huge commit.
- Prefer meaningful development milestones over arbitrary commit counts.

A person reading the history should be able to understand how the project evolved.

## 4. Git Safety

Before suggesting or performing a commit:

1. Inspect `git status`.
2. Review the actual file changes.
3. Check for accidental modifications and generated files.
4. Ensure no API keys, passwords, tokens, or secrets are staged.
5. Verify that the commit message accurately describes the changes.
6. Respect the existing `.gitignore`.
7. Never delete or rewrite existing Git history without explicit permission.
8. Never force-push, reset, rebase, or amend published commits without explicit approval.
9. Never change branches or repositories unexpectedly.

**Default behavior:** Propose the commit and its message, but let the user approve it before committing or pushing.

## 5. Core Philosophy

**Clarity over complexity. Accuracy over appearance. Meaningful changes over commit quantity.**

Do not try to make commit history look artificially human or conceal how development happened.

The goal is a professional, understandable, and trustworthy Git history that accurately represents the work performed.

