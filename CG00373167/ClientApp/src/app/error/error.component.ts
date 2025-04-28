import { Component } from '@angular/core';
import { Router } from '@angular/router';

/**
 * ErrorComponent
 * Renders an error page showing status, message, and a link back home.
 */
@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent {
  status: number;
  message: string;

  constructor(private router: Router) {
    // Pull data from history.state (set during navigation)
    const state = window.history.state as {
      status?: number;
      message?: string;
    };
    this.status = state.status ?? 500;
    this.message = state.message ?? 'An unexpected error occurred.';
  }

  goHome(): void {
    this.router.navigate(['/vehicles']);
  }
}